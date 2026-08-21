#!/usr/bin/env bash
#
# Cleanup script for school-projects repo bloat.
# Run with --dry-run first to see what would be deleted, nothing is
# touched in dry-run mode. Run again without the flag to actually delete.
#
# Usage:
#   ./cleanup-bloat.sh /path/to/school-projects --dry-run
#   ./cleanup-bloat.sh /path/to/school-projects
#
set -euo pipefail

TARGET_DIR="."
DRY_RUN=false
for arg in "$@"; do
  if [[ "$arg" == "--dry-run" ]]; then
    DRY_RUN=true
  else
    TARGET_DIR="$arg"
  fi
done

if [[ ! -d "$TARGET_DIR" ]]; then
  echo "Error: '$TARGET_DIR' is not a directory."
  exit 1
fi

cd "$TARGET_DIR"
echo "Scanning: $(pwd)"
echo "Dry run: $DRY_RUN"
echo

# ============================================================
# TIER 1: SAFE TO AUTO-DELETE
# Only things that are either (a) a regenerable build/venv/cache
# artifact, (b) an installed application (not your code), or
# (c) pure scratch/tmp output your own program generated.
# Nothing here is external input data you can't get back.
# ============================================================

ECLIPSE_FILES=(".classpath" ".project")
ECLIPSE_DIRS=(".settings")

# Regenerable build/dependency/cache directories
BLOAT_DIRS=("node_modules" "bin" "target" "build" "build-debug" "out" "dist" "__pycache__" ".idea" ".venv" "venv")

# Whole folders that are bundled applications or downloaded example
# code, not your own authored work. EDIT THIS LIST if it's wrong for
# your setup, these are matched as exact relative paths from TARGET_DIR.
WHOLE_FOLDER_DELETES=(
  "Tdt4250/eclipse"
  "Tdt4250/tdt4250-examples"
)

# Known scratch/tmp output folders (regenerable by re-running your code)
SCRATCH_DIRS=(
  "Inf3203/assignment-1/src/mr_tmp"
)

# Windows download-tracking metadata, never useful in a repo
ZONE_IDENTIFIER_PATTERN="*Zone.Identifier"

echo "== TIER 1: Safe to auto-delete =="
echo
echo "-- Nested .git directories (excluding repo root) --"
find . -mindepth 2 -type d -name ".git" -print

echo
echo "-- Eclipse metadata (.settings, .classpath, .project) --"
for d in "${ECLIPSE_DIRS[@]}"; do find . -type d -name "$d" -print; done
for f in "${ECLIPSE_FILES[@]}"; do find . -type f -name "$f" -print; done

echo
echo "-- Build/dependency/cache directories --"
for d in "${BLOAT_DIRS[@]}"; do find . -type d -name "$d" -print; done

echo
echo "-- Whole non-authored folders (bundled apps / downloaded examples) --"
for d in "${WHOLE_FOLDER_DELETES[@]}"; do
  [[ -d "$d" ]] && echo "./$d"
done

echo
echo "-- Known scratch/tmp output --"
for d in "${SCRATCH_DIRS[@]}"; do
  [[ -d "$d" ]] && echo "./$d"
done

echo
echo "-- Zone.Identifier files --"
find . -type f -name "$ZONE_IDENTIFIER_PATTERN" -print

# ============================================================
# TIER 2: FLAGGED FOR MANUAL REVIEW ONLY
# Potentially irreplaceable input/data files. NEVER auto-deleted,
# regardless of dry-run flag. You decide what to do with these.
# ============================================================

echo
echo "== TIER 2: Flagged for manual review (NEVER auto-deleted) =="
echo
echo "-- Known large data files worth checking before removing --"
KNOWN_REVIEW_FILES=(
  "Inf3203/assignment-1/src/data-pr/input-large.txt"
  "Inf3203/assignment-1/src/data-pr/input-small.txt"
  "Inf1400/submission-2/sudoku/sudoku_1M.csv"
)
for f in "${KNOWN_REVIEW_FILES[@]}"; do
  [[ -f "$f" ]] && du -sh "$f"
done

echo
echo "-- Any OTHER file over 20MB not already covered above (auto-detected) --"
echo "   (shown so nothing large slips through unnoticed)"
find . -type f -size +20M \
  ! -path "*/node_modules/*" ! -path "*/.venv/*" ! -path "*/venv/*" \
  ! -path "*/build/*" ! -path "*/build-debug/*" ! -path "*/target/*" \
  ! -path "*/Tdt4250/eclipse/*" ! -path "*/Tdt4250/tdt4250-examples/*" \
  ! -path "*/Inf3203/assignment-1/src/mr_tmp/*" \
  -exec du -sh {} \; 2>/dev/null | sort -rh

echo
echo "== Size of Tier 1 items (what would actually be deleted) =="
{
  find . -mindepth 2 -type d -name ".git"
  for d in "${ECLIPSE_DIRS[@]}" "${BLOAT_DIRS[@]}"; do find . -type d -name "$d"; done
  for f in "${ECLIPSE_FILES[@]}"; do find . -type f -name "$f"; done
  for d in "${WHOLE_FOLDER_DELETES[@]}" "${SCRATCH_DIRS[@]}"; do [[ -d "$d" ]] && echo "$d"; done
  find . -type f -name "$ZONE_IDENTIFIER_PATTERN"
} | xargs -I{} du -sh "{}" 2>/dev/null | sort -rh | head -30

if [[ "$DRY_RUN" == true ]]; then
  echo
  echo "Dry run complete. Nothing was deleted."
  echo "Tier 1 items above would be deleted on a real run."
  echo "Tier 2 items are NEVER auto-deleted, review them yourself."
  exit 0
fi

echo
echo "NOTE: Tier 2 (flagged) files will NOT be touched by this script,"
echo "no matter what you answer below. Delete those manually if you"
echo "decide they're safe to lose."
echo
read -p "About to DELETE all Tier 1 items listed above. Type 'yes' to continue: " CONFIRM
if [[ "$CONFIRM" != "yes" ]]; then
  echo "Aborted."
  exit 1
fi

# Actually delete -- Tier 1 only
find . -mindepth 2 -type d -name ".git" -exec rm -rf {} +
for d in "${ECLIPSE_DIRS[@]}" "${BLOAT_DIRS[@]}"; do
  find . -type d -name "$d" -exec rm -rf {} +
done
for f in "${ECLIPSE_FILES[@]}"; do
  find . -type f -name "$f" -delete
done
for d in "${WHOLE_FOLDER_DELETES[@]}" "${SCRATCH_DIRS[@]}"; do
  [[ -d "$d" ]] && rm -rf "$d"
done
find . -type f -name "$ZONE_IDENTIFIER_PATTERN" -delete

echo "Done. Tier 1 bloat removed. Tier 2 flagged files were left untouched."
echo "Next steps: git add -A && git commit -m 'Clean up repo bloat'"